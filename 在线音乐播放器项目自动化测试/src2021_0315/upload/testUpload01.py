from selenium import webdriver
import time


browser = webdriver.Chrome()
browser.get("http://123.207.204.18:8080/OnlineMusic/list.html")
browser.maximize_window()
time.sleep(3)

# 定位添加歌曲并点击
browser.find_element_by_link_text("添加歌曲").click()
time.sleep(3)
# 定位上传并点击
browser.find_element_by_xpath("/html/body/form/table/tbody/tr[2]/td[2]/input").click()
time.sleep(6)
browser.quit()

