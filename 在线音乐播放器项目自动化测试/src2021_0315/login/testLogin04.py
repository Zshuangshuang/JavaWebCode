from selenium import webdriver
import time

from selenium.webdriver.common.keys import Keys

browser = webdriver.Chrome()
browser.get("http://123.207.204.18:8080/OnlineMusic/login.html")
browser.maximize_window()
time.sleep(2)

# 定位输入框,并模拟从键盘输入姓名
browser.find_element_by_name("username").send_keys("李白")
browser.find_element_by_name("username").send_keys(Keys.TAB)
# 定位输入框，并模拟从键盘输入密码
browser.find_element_by_id("password").send_keys("234")
# 点击登录按钮
browser.find_element_by_id("submit").click()
time.sleep(6)
browser.quit()