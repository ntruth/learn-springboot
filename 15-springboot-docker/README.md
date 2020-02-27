## 实现如何使用docker打包springboot项目

1.增加dockerfile文件
    1.1 在src/main新增docker文件夹并创建Dockerfile文件
    内容如下 注意app名称
FROM openjdk:8-jre
VOLUME /tmp
ADD ${项目的jar包名称}.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar”]


2.编辑pom.xml新增docker插件
<!-- docker打包插件  -->
<plugin>
    <groupId>com.spotify</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version>0.4.13</version>
    <configuration>
        <imageName>app</imageName>
        <dockerDirectory>src/main/docker</dockerDirectory>
        <resources>
            <resource>
                <targetPath>/</targetPath>
                <directory>${project.build.directory}</directory>
                <include>${project.build.finalName}.jar</include>
            </resource>
        </resources>
    </configuration>
</plugin>

3.构建docker镜像
mvn clean package docker:build

4.运行镜像
docker run -d -name app -p 8080:8080 app

