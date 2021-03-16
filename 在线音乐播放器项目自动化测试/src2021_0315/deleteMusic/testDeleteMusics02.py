from selenium import webdriver
import time

browser = webdriver.Chrome()
browser.get("http://127.0.0.1:8080/OnlineMusic/list.html")
browser.maximize_window()
time.sleep(3)

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
