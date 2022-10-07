## Ticket Alert System, with integration with GLPI API.

To run, you need enable the API in GLPI

In the "config.properties" file we have the following fields.

+ update.updateTime = 1 *Time to update tickets in minutes*
+ position.height = 55 *Initial distance from top of screen*

*Obs. Remember to put the range if IP authorized on the API config.*

![image](https://user-images.githubusercontent.com/77954907/194460868-c1a78507-942a-403c-be84-fd569ad7cb03.png)

### In the configuration screen you must fill in the following fields:

+ URL = Your system URL ex.: http://yourdomain.com/glpi
+ User Token = This token you can generate inside a user's information screen
+ App Token = This one you will have to configure when releasing the access group in the API

You can follow the step by step of this article to configure the api and get the tokens: https://tic.gal/en/how-to-setup-glpi-api/

## Screenshots:

![image](https://user-images.githubusercontent.com/77954907/194463764-f96ed107-6100-4802-85a3-0f009905aa57.png)

![image](https://user-images.githubusercontent.com/77954907/194463891-4041cbf9-ea2f-44a5-be97-d5a5665c5f3d.png)

*If there is no ticket, the screen disappears*


*[Download - MediaFire (compiled)](https://www.mediafire.com/file/vud9e5w97rnkpbd/PopUp_GLPI.rar/file) Old*
