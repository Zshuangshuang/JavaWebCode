from selenium import webdriver
import time

browser = webdriver.Chrome()
browser.get("http://123.207.204.18:8080/OnlineMusic/list.html")
browser.maximize_window()
time.sleep(3)

# 定位喜欢按钮
browser.find_element_by_xpath("/html/body/div/table/tbody[2]/tr[1]/td[4]/button[2]").click()
time.sleep(6)
browser.quit()

