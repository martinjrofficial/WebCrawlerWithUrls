Run buildwebcrawler and the output is generated in a file called testing which will be in the project directory

Implemenation:
Jsoup was used for this project because it easily allows to parse HTML data of a website for data manipulation and extraction.
A list is used in this node in such a way that each node url has several children node urls depending on how many urls present in that url. 
The child nodes also have it own children nodes aswell until all the urls within the website has been parsed
This was easily mantained by checking a seperate hashset which keep tracks of all crawled urls and checking to see if the url thats about to be crawled contains the starting url
So the webcrawler here works like an hashset datastructure but for strings where as a child node could have its own children nodes.


If i had more time;
The web crawler could have been improved by taking cli input
I could create new arrays with every instance
I could find a way to use less memory
I could find a way to make the program run faster hence reducing computational time
