{:title "cryogen in github pages "
 :layout :post
 :toc false
 :disqus-shortname ""}

#### ... because it wasn't so easy :) ... [issue](https://github.com/lacarmen/cryogen/issues/36)
I'll share here the process that I'd to follow to get [cryogen](https://github.com/lacarmen/cryogen) (a great clojure static site generator) finally working in [github pages](https://pages.github.com/).   

### 1. setup two github repos
1. follow [github instructions](https://help.github.com/articles/create-a-repo/) and create a repo named "your-blog" 
2. follow [github instructions](https://help.github.com/articles/create-a-repo/) and create a repo named "your-github-name-account.github.com" (in my case tangrammer.github.com)

### 2. create locally your cryogen blog project  
	1. `$ lein new cryogen your-blog`
	2. `$ cd your-blog`

### 3 .configure cryogen to work with github pages
change resources/templates/config.edn    
`:blog-prefix      "" `




### 4. configure github repos  
	3. `$ git init` 
	4. add `/resources/public` line to your-blog/.gitignore file
	4. `$ git add . &&  git commit -m "first commit"`
	4. `$ git remote add origin git@github.com:your-github-name-account/your-blog.git && git push -u origin master`
	5. `$ git clone git@github.com:your-github-name-account/your-github-name-account.github.com.git resources/public`

### 5. start cryogen and edit your resources *.md
 `$ lein ring server`

   
### 6. publish your changes

`$ git add . && git commit -am "WIP" && git push`
`$ cd resources/public &&  git add . && git commit -am "WIP" && git push && cd ../../`   	
	
### (Loop) => Instructions to make changes
```
cd your-blog
lein ring server
.... edit your site to get new web version ....
... and push changes to both repos ....
git add . && git commit -am "WIP" && git push
cd resources/public &&  git add . && git commit -am "WIP" && git push && cd ../../

```

**PS: now using [MacDown](http://macdown.uranusjr.com/) as Markdown editor client**

