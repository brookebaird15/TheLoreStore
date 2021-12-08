# Personal Book Tracker
This is a personal book tracking tool. It allows a user to track the books they are reading or have read in one major list, or they can view book titles by author or genre. It also displays graphical data on the genre makeup of their collection, and the reading completion status of their collection.

***
## Getting Started
### Login
The user must first log in to the application where they are prompted to enter their database name, username, and password.

### Main Table
Once sucessfully logged in, they will be directed to the main book table which displays all the books stored in their database. From the book table, they can delete individual books or they can navigate to the add/update screen.

### Adding Books
In the add item screen, the user may enter the new item information. The application takes text input for the title, year of publishing, and comment fields. Users can select a radio button to declare if a book is unread, in progress, or completed. The screen also has drop-down menus for selecting authors, genres, and a publisher. Once completed the save button directs the user back to the main table.

### Updating Books
In the update screen, users can edit the information of a selected book. The fields in the update screen automatically generate with the current information. Users can change all information, including updating the status if they started or finished a book, as well as adding or removing authors and genres. Once completed the save button directs the user back to the main table.

*Note* - If a user adds the same author or genre from the drop down lists multiple times, it will not add duplicate entries. A book can only have one reference to a specific author or genre.

### Author Tab
The author tab lists all authors in the database. Users can add a new author or they can select an author to update. When a user selects an item in the table, the page will list all books written by the selected author. To prevent a book from having null authors, users cannot delete authors once entered, but their first, middle, and last names can be updated.

### Genre Tab
The genre tab lists all genres in the database. Users can add a new genre or they can select a genre to update. When a user selects an item in the table, the page will list all books that are under that genre. To prevent a book from having null genres, users cannot delete items once entered to this table, but the name can be updated.

### Publisher Tab
The publisher tab lists all publishers in the database. Users can add a new item or they can select a one to update. To prevent a book from having null publishers, users cannot delete items once entered but they can update the name.

### Stats Tabs
There are two statistics tabs. The Status Stats tab contains a pie chart displaying the proportion of books in the user's collection that are unread, in progress, or completed. 
The Genre Stats tab contains a pie chart displaying a breakdown of the genres contained in the user's collection. It only displays genres that have at least one book, so if a user adds a genre without associating it to a book, it will not be added to the chart for simplicity's sake.

***
## Prerequesites
Before trying to run the application for the first time, users must create a file called 'DBConst' in the Database package.

![image](https://user-images.githubusercontent.com/90527594/145234090-347d5eb7-94ca-4a64-9e69-f6f8aeaaf5dd.png)

Inside the DBConst, users must declare the following properties:
```
public static String DB_NAME = "";
public static String DB_USER = "";
public static String DB_PASS = "";
```
These strings can be declared as empty strings.

***
### Database Layout
The database contains the following tables:
- Book
- Status
- Publisher
- Genre
- A genre/book joining table
- Author
- An author/book joining table
- A book view to gather and format all information

![image](https://user-images.githubusercontent.com/90527594/145244613-65a574f9-63db-4539-9487-fdef94c03476.png)

***
**Authors:** Ashley McCallum and Brooke Baird
