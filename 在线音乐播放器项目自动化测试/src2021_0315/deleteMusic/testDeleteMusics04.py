from selenium import webdriver
import time
from selenium.webdriver.common.keys import Keys

browser = webdriver.Chrome()
browser.get("http://127.0.0.1:8080/OnlineMusic/login.html")
browser.maximize_window()
time.sleep(2)

# 定位输入框,并模拟从键盘输入姓名
browser.find_element_by_name("username").send_keys("樱桃小丸子")
browser.find_element_by_name("username").send_keys(Keys.TAB)
# 定位输入框，并模拟从键盘输入密码
browser.find_element_by_id("password").send_keys(123)
# 点击登录按钮
browser.find_element_by_id("submit").click()
time.sleep(6)

# 定位勾选框
inputs = browser.find_elements_by_tag_name('input')
for i in range(0, 4):
    if inputs[i].get_attribute('type') == "checkbox":
        inputs[i].click()
time.sleep(3)
# 定位删除选中并点击
browser.find_element_by_link_text("删除选中").click()
time.sleep(3)
browser.quit()