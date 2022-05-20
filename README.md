# qTechnologies Auth Sample
Sample for sellers on our marketplace to check for a users license and updates

## Send Request
You will send a POST request to https://verify.mdma.dev/customer.php with the query of userId and resourceId.

Those IDs will be (if using placeholders like in the sample) injected on download.

### Placeholders
Placeholders that will be replaced on download:

- %%QTECH_USER_ID%% - Will be replaced by users website userId.
- %%QTECH_RESOURCE_ID%% - Will be replaced by resourceId of the downloaded file.
- %%QTECH_TIME%% - Will be replaced by a timestamp
  when the file was downloaded.

### Injector
The resource injector can only replace placeholders in .jar files, this means it will replace the placeholders on compiled .class files inside the jar.

## Response from Server
If the license has been successfully verified and the user isn't banned the response will look like this:

    {"status":"SUCCESS","username": "username", version":"1.7.4"}

If something failed i.e the user is banned a response will look like this:

    {"status":"BANNED"}


## IMPORTANT
This sample shouldn't be used 1:1 in your spigot plugin, program or client as its just to show how it works and is pretty easily crackable.

