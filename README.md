This Project is for CRUD Operations on SQLITE though Android App.
CAR RENTAL APPLICATION:
Create a simple and friendly GUI interface that would be able to perform the following tasks. You must
connect your DB to a mobile application of some sort. It can be android studios/ iOS/ Flutter. For each
query, you need to provide some info to the user about the query purpose. The user will have to type or
select the query’s input parameters and post the question to your program. The program needs to return all
result’s rows. You also need to print the count of rows returned. How the UI looks is up to you and your
group members.

REQUIREMNETS:
1. The first requirement is to add information about a new customer. Do not provide the customer ID in
   your query. Submit your editable SQL query that your code executes.
2. The second requirement is to add all the information about a new vehicle. Submit your editable SQL
   query that your code executes.
3. The third requirement is to add all the information about a new rental reservation (this must find a
   free vehicle of the appropriate type and category for a specific rental period). We assume that the
   customer has the right either to pay at the order or return date. Submit your editable SQL queries
   (select available vehicles & insert rental) that your code executes.
4. The fourth requirement is to handle the return of a rented car. This transaction should print the total
   customer payment due for that rental, enter it in the database and update the returned attribute
   accordingly. You need to be able to retrieve a rental by the return date, customer name (the table
   needs the id), and vehicle info. Submit your editable SQL queries (retrieve & update rental) that your
   code executes.
5. The fifth requirement is to return the view’s results by applying the following criteria:  
   a. List for every customer the ID, name, and if there is any remaining balance. The user has the
   right to search either by a customer’s ID, name, part of the name, or to run the query with no
   filters/criteria. The amount needs to be in US dollars. For customers with zero (0) or NULL
   balance, you need to return zero dollars ($0.00). Make sure that your query returns
   meaningful attribute names. In the case that the user decides not to provide any filters, order
   the results based on the balance amount. Make sure that you return all records. Submit your
   editable SQL query that your code executes.
   b. List for every vehicle the VIN, the description, and the average DAILY price. The user has
   the right either to search by the VIN, vehicle’s description, part of the description, or to run
   the query with no filters/criteria. An example criterion would be all ‘BMW’ vehicles. The
   amount needs to be in US dollars. The average DAILY price derives from the rental table,
   and the amount needs to have two decimals as well as the dollar ‘$’ sign. For vehicles that
   they do not have any rentals, you need to substitute the NULL value with a ‘Non-Applicable’
   text. Make sure that your query returns meaningful attribute names. In the case that the user
   decides not to provide any filters, order the results based on the average daily price. Submit
   your editable SQL query that your code executes.
   
   
   
![image](https://user-images.githubusercontent.com/31099562/167290443-0e104e60-115e-472a-b5dc-7e112720016b.png)

![image](https://user-images.githubusercontent.com/31099562/167290473-ea1c8a1e-0ae3-484f-8dd8-4e6c6dd84e28.png)

![image](https://user-images.githubusercontent.com/31099562/167290492-77c82fd8-470c-4e21-bf46-04b78e04725d.png)


