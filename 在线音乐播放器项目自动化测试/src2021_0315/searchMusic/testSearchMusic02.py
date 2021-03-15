from selenium import webdriver
import time

browser = webdriver.Chrome()
browser.get("http://123.207.204.18:8080/OnlineMusic/list.html")
browser.maximize_window()
time.sleep(3)
# 定位输入框并输入查找音乐的关键字
browser.find_element_by_id("exampleInputName2").send_keys("是你")
# 定位查询按钮并点击
browser.find_element_by_xpath("//*[@id='submit1']").click()
time.sleep(8)
browser.quit()
