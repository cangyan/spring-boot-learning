# IDEA livereloading设置
- 添加chrome extension `LiveReload`
- 添加`spring-boot-devtools`依赖
- IDEA设置file->settings->build,execution,deployment. Go to ->compiler->build project automatically
- IDEA输入`Ctrl+Shift+A`选择registry-> compiler.automake.allow.when.app.running
- 开发时， 选择spring boot程序运行，不要用gradle运行，不然不会自动编辑