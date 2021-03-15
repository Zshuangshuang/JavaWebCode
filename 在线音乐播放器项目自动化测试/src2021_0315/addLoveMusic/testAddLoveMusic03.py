from selenium import webdriver
import time
from selenium.webdriver.common.keys import Keys

browser = webdriver.Chrome()
browser.get("http://123.207.204.18:8080/OnlineMusic/login.html")
browser.maximize_window()
time.sleep(3)

# 定位输入框,并模拟从键盘输入姓名
browser.find_element_by_name("username").send_keys("貂蝉")
browser.find_element_by_name("username").send_keys(Keys.TAB)
# 定位输入框，并模拟从键盘输入密码
browser.find_element_by_id("password").send_keys(789)
# 点击登录按钮
browser.find_element_by_id("submit").click()
time.sleep(6)

# 定位喜欢按钮
browser.find_element_by_xpath("/html/body/div/table/tbody[2]/tr[3]/td[4]/button[2]").click()
time.sleep(3)
browser.quit()

