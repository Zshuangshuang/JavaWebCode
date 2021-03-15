from selenium import webdriver
import time
from selenium.webdriver.common.keys import Keys


browser = webdriver.Chrome()
browser.get("http://123.207.204.18:8080/OnlineMusic/register.html")
browser.maximize_window()
time.sleep(3)

# 定位输入框并输入相应内容
browser.find_element_by_id("user").send_keys()
browser.find_element_by_id("user").send_keys(Keys.TAB)
browser.find_element_by_id("password").send_keys()
browser.find_element_by_id("password").send_keys(Keys.TAB)
browser.find_element_by_id("age").send_keys()
browser.find_element_by_id("age").send_keys(Keys.TAB)
browser.find_element_by_id("gender").send_keys()
browser.find_element_by_id("gender").send_keys(Keys.TAB)
browser.find_element_by_id("email").send_keys()
browser.find_element_by_id("email").send_keys(Keys.TAB)

# 将滚动条拉到页面最底端
js1 = "var q=document.documentElement.scrollTop=10000"
browser.execute_script(js1)
# 点击注册按钮
browser.find_element_by_id("submit").click()

time.sleep(4)
browser.quit()
