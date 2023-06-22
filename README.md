# Java Templates

I am so tired of looking for simple examples of things to make a quick start when 
I begin a project or add a new function to a project. Somehow the art of writing 
good tutorials and HOWTOs has been lost. So this repository will contain templates 
for things that I use regularly, but seldom enough to forget how to quickly implement 
them. With quickly I mean within 5 or 10 minutes.

I use IntelliJ so this project includes an .idea directory which I hope means that when
cloned you can immediately run the examples. If this does not happen, please create
an issue and I'll see what I can do.

I have had to include the sqlite jdbc driver because the Maven dependency just doesn't 
work and I would like these examples to work straight after download. You will have to 
manually check for updates to the driver, download it and copy it into place, which is the 
lib directory below sqlite.