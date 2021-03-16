
from selenium import webdriver
import time

browser = webdriver.Chrome()
browser.get("http://127.0.0.1:8080/OnlineMusic/list.html")
browser.maximize_window()
time.sleep(3)

# 定位喜欢列表并点击
browser.find_element_by_link_text("喜欢列表").click();
time.sleep(3)
browser.quit()
