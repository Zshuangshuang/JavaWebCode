from selenium import webdriver
import time
from selenium.webdriver.common.keys import Keys


browser = webdriver.Chrome()
browser.get("http://123.207.204.18:8080/OnlineMusic/login.html")
browser.maximize_window()
time.sleep(3)
# 定位输入框,并模拟从键盘输入姓名
browser.find_element_by_name("username").send_keys("鲁班")
browser.find_element_by_name("username").send_keys(Keys.TAB)
# 定位输入框，并模拟从键盘输入密码
browser.find_element_by_id("password").send_keys(999)
# 点击登录按钮
browser.find_element_by_id("submit").click()
time.sleep(6)


# 定位添加歌曲并点击
browser.find_element_by_link_text("添加歌曲").click()
time.sleep(3)

# 定位选择文件并上传文件
browser.find_element_by_xpath("/html/body/form/table/tbody/tr[1]/td[2]/input").send_keys(r"E:\CloudMusic\放生.mp3")
time.sleep(6)
# 定位上传并点击
browser.find_element_by_xpath("/html/body/form/table/tbody/tr[2]/td[2]/input").click()
time.sleep(20)


# 定位输入歌手并点击提交
browser.find_element_by_name("singer").send_keys("陈阳")
browser.find_element_by_xpath("/html/body/form/div[2]/input").click()
time.sleep(9)

browser.quit()

