# Github 用户名可用性测试，用户名长度为 3 位的可用性测试，简短的 Github 用户名。

# 致谢
**感谢 GitHub 提供了一个开放、协作的平台，也衷心感谢 GitHub 社区的每一位开发者和贡献者。** 

# 用户名规则
1. 长度限制：必须在 1 到 39 个字符之间，[1,39]。
2. 允许的字符：
   - 只能使用字母 (a-z)、数字 (0-9)、连字符 (-)。
   - 连字符不能作为开头或结尾。
3. 用户名不区分大小写，但显示时会保留原始格式。

# 使用
## 说明
用户名的可用性随着时间和不可遇见错误等发生变化。  
当前用户名的可用性测试时间是截止到2024年11月25日。
## 当前可用的用户名列表
```
z-y
z-7
z-8
z1o
7u0
x-4
x-5
x-6
x-8
```
## 基础环境
JDK11
## 运行
1. 先进行目录配置，修改`GithubNameCheckTest`类的`filepath`变量。
2. 生成用户名列表文件，执行`GithubNameCheckTest`类的`generateName`方法，等待执行完成。
3. 根据用户名列表文件检验用户名是否可用，执行`GithubNameCheckTest`类的`checkName`方法，查看控制台或文件结果。

# 参考
https://github.com/able8/Available-Short-Github-Username
