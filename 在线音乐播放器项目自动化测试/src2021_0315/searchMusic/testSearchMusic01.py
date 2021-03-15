from selenium import webdriver
import time

browser = webdriver.Chrome()
browser.get("http://123.207.204.18:8080/OnlineMusic/list.html")
browser.maximize_window()
time.sleep(3)

# 定位查询按钮并点击
browser.find_element_by_xpath("//*[@id='submit1']").click()
time.sleep(5)
browser.quit()
