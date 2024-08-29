# Live Project Selenium 2 - Guru99
<p align="left">
	<img src="https://img.shields.io/badge/HTML5-E34F26.svg?style=flat&logo=HTML5&logoColor=white" alt="HTML5">
	<img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=openjdk&logoColor=white" alt="java">
  <img src="https://img.shields.io/badge/Selenium-43B02A.svg?style=flat&logo=Selenium&logoColor=white" alt="selenium">
  <img src="https://img.shields.io/badge/Apache%20Maven-C71A36.svg?style=flat&logo=Apache-Maven&logoColor=white" alt="maven">
</p>

---

## 📂 Repository Structure

```sh
└── Automate-Ecommerce-Selenium/
    ├── Readme.md
    ├── pom.xml
    ├── screenshot
    ├── src
    │   └── test
    │       └── java
    │           └── com
    │               └── example
    │                   └── test
    │                       ├── Day1Test.java
    │                       ├── Day2Test.java
    │                       ├── Day3Test.java
    │                       ├── Day4Test.java
    │                       └── Day5Test.java
    ├── test-output
    │   ├── All Test Suite
    │   │   └── testng-failed.xml
    │   ├── emailable-report.html
    │   └── testng-failed.xml
    └── testng.xml
```
---
## 10 Days challenge
<details closed><summary>Test Case</summary>
  
| Sr # | Test Case                                                                                                                                   | Test Steps                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      | Test Data                                                                                                                                                                                                            | Expected Results                                                                                                                                                                    | Special Notes | Pass/Fail |
|------|---------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------|-----------|
| 1    | Verity item in Mobile List page can be sorted by 'Name'                                                                                     | 1\. Goto http://live.techpanda.org//<br>2\. Verify Title of the page<br>3\. Click On 'MOBILE' menu<br>4\. Verity Title of the page<br>5\. In th e list of alt mobile, select SORT BY dropdown as name'<br>6\. Verify all products are sorted by name                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |                                                                                                                                                                                                                      | 1) Text 'THIS IS DEMO SITE' shown in home page<br>2) Title 'MOBILE' is shown on mobile list page<br>3) All products sorted by name                                                  |               | .         |
| 2    | Verity that cost of product in list page and details page are equal                                                                         | 1\. Goto http://live.techpanda.org//<br>2\. Click On 'MOBILE' menu<br>3\. In the list of all mobile, read the cost of Sony Xperia mobile), Note this value<br>4\. Click on Sony Xperia mobile<br>5\. Read the Sony Xperia mobile from detail page.<br>6\. Compare value in Step 3 & Step 5                                                                                                                                                                                                                                                                                                                                                                                                                                                      |                                                                                                                                                                                                                      | 1) product Value in list and details page should be equal ($100)                                                                                                                    |               | .         |
| 3    | Verity that you cannot add more product in cart than the product available in store                                                         | 1\. Goto http://live.techpanda.org/<br>2\. Click on 'Mobile' menu<br>3\. In the list of all mobile, click on 'ADD TO CART' for Sony Xperia mobile<br>4\. Change 'QTY' value to 1000 and clock 'UPDATE' button.<br>5\. Verify the error message<br>6\. Then click on 'EMPTY CART' link in th footer of list of all mobiles.<br>7\. Verify cart is empty                                                                                                                                                                                                                                                                                                                                                                                          |                                                                                                                                                                                                                      | 1) On click update button an error is shown 'The request quantity for "Sony Xperia" is not available.'<br>2) On click empty cart button a message 'SHOPPING CART IS EMPTY' is shown |               | .         |
| 4    | Verify that you are able to compare two product                                                                                             | 1\. Goto http://live.techpanda.org/<br>2\. Click on 'MOBILE' menu<br>3\. In mobile products list, click on 'Add To Compare' for 2 mobiles<br>4\. Click on 'COMPARE' button.<br>5\. Verify the pop-up window and check that the products are reflected in it<br>6\. Close the1 Popup Windows                                                                                                                                                                                                                                                                                                                                                                                                                                                     | Phone 1 - Sony Xperia<br>Phone 2 - IPHONE                                                                                                                                                                            | 1\. A Popup window opens with heading as 'COMPARE PRODUCTS' and the selected products are present in it.<br>2\. Popup window is closed                                              |               | .         |
| 5    | Verify you can create account in E-commerce site and can share wishlist to other people using email                                         | 1\. Go to http://live.techpanda.org/<br>2\. Click on My Account link<br>3\. Click Create Account link and fill New User information except Email ID<br>4\. Click Register<br>5\. Verify Registration is done<br>6\. Go to TV menu<br>7\. Add product in your wish list<br>8\. Click SHARE WISHLIST<br>9\. In next page enter EMail and a message and click SHARE WISHLIST<br>10\. Check wishlist is shared                                                                                                                                                                                                                                                                                                                                      | product = LG LCD                                                                                                                                                                                                     | 1) Account Registration done<br>2) Wishlist Shared Successfully                                                                                                                     |               | .         |
| 6    | Verify user is able to purchase product using registered email id (USE CHROME BROWSER)                                                      | 1\. Goto http://live.techpanda.org/<br>2\. Click on my account link<br>3\. Login in application using previously created credential.<br>4\. Click on MY WISHLIST link<br>5\. In next page.  Click ADD TO CART link<br>6\. Click PROCEED TO CHECKOUT<br>7\. Enter Shipping Information<br>8\. Click Estimate<br>9\. Verify Shipping cost generated<br>10\. Select Shipping Cost, Update Total<br>11\. Verify shipping cost is added to total<br>12\. Click "Proceed to Checkout"<br>13\. Enter Billing Information<br>14\. In Shipping Method.  Click Continue<br>15\. In Payment Information select 'Check/Money Order' radio button. Click Continue<br>16\. Click 'PLACE ORDER' button<br>17\. Verify Oder is generated. Note the order number | 1) User credentials created in test case 05<br>2) Shipping Information<br>   Country = United States<br>   States = New York<br>   Zip = 542896<br>   Address = ABC<br>   City = New York<br>   Telephone = 12345678 | 1) Flat Rate Shipping of $ is generated<br>2) Shipping cost is added to total product cost<br>3) Order is placed. Order number is generated                                         |               | .         |
| 7    | Verify that you will be able to save previously placed order as a pdf file                                                                  | 1\. Go to http://live.techpanda.org/<br>2\. Click on My Account link<br>3\. Login in application using previously created credential<br>4\. Click on 'My Orders'<br>5\. Click on 'View Order'<br>6\. Verify the previously created order is displayed in 'RECENT ORDERS' table and status is Pending<br>7\. Click on 'Print Order' link<br>8\. Verify Order is saved as PDF                                                                                                                                                                                                                                                                                                                                                                     | Use Firefox                                                                                                                                                                                                          | 1\. Previously created order is displayed in 'RECENT ORDERS' table and status is Pending.<br>2\. Order is saved as PDF                                                              |               | .         |
| 8    | Verify you are able to change or reorder previously added product                                                                           | 1\. Go to http://live.techpanda.org/<br>2\. Click on My Account link<br>3\. Login in application using previously created credential<br>4\. Click on 'REORDER' link, change QTY & click Update<br>5\. Verify Grand Total is changed<br>6\. Complete Billing & Shipping Information<br>7\. Verify order is generated and note the order number                                                                                                                                                                                                                                                                                                                                                                                                   | QTY-10                                                                                                                                                                                                               | 1) Grand Total is Changed<br>2) Order number is generated                                                                                                                           |               | .         |
| 9    | Verify Discount Coupon works correctly                                                                                                      | 1\. Go to http://live.techpanda.org/<br>2\. Go to Mobile and add IPHONE to cart<br>3\. Enter Coupon Code<br>4\. Verify the discount generated correctly                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         | Coupon Code: GURU50                                                                                                                                                                                                  | 1) Price is discounted by 5%                                                                                                                                                        |               | .         |
| 10   | Export all Orders in csv file and display these information in console and you are able to send this file to another email id as attachment | 1\. Go to http://live.techpanda.org/index.php/backendlogin<br>2\. Login the credentials provided<br>3\. Go to Sales-> Orders menu<br>4\. Select 'CSV' in Export To dropdown and click Export button.<br>5\. Read downloaded file and display all order information in console<br>6\. Attach this exported file and email to another email id                                                                                                                                                                                                                                                                                                                                                                                                    |                                                                                                                                                                                                                      | 1) Console displays all order information<br>2) Email is sent successfully                                                                                                          |               | .         |

</details>

---
