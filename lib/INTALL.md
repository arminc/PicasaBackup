# Install
Google does not provide the following libraries in the maven repository so you need to add them manually.

## Photos
mvn install:install-file -Dfile=gdata-photos-2.0.jar -DgroupId=com.google -DartifactId=gdata-photos -Dversion=2.0 -Dpackaging=jar

<dependency>
	<groupId>com.google</groupId>
	<artifactId>gdata-photos</artifactId>
	<version>2.0</version>
	<type>jar</type>
</dependency>

## Photos meta
mvn install:install-file -Dfile=gdata-photos-meta-2.0.jar -DgroupId=com.google -DartifactId=gdata-photos-meta -Dversion=2.0 -Dpackaging=jar

<dependency>
	<groupId>com.google</groupId>
	<artifactId>gdata-photos-meta</artifactId>
	<version>2.0</version>
	<type>jar</type>
</dependency>

## Media
mvn install:install-file -Dfile=gdata-media-1.0.jar -DgroupId=com.google -DartifactId=gdata-media -Dversion=1.0 -Dpackaging=jar

<dependency>
	<groupId>com.google</groupId>
	<artifactId>gdata-media</artifactId>
	<version>1.0</version>
	<type>jar</type>
</dependency>

## Core
mvn install:install-file -Dfile=gdata-core-1.0.jar -DgroupId=com.google -DartifactId=gdata-core -Dversion=1.0 -Dpackaging=jar

<dependency>
	<groupId>com.google</groupId>
	<artifactId>gdata-core</artifactId>
	<version>1.0</version>
	<type>jar</type>
</dependency>

## Client
mvn install:install-file -Dfile=gdata-client-1.0.jar -DgroupId=com.google -DartifactId=gdata-client -Dversion=1.0 -Dpackaging=jar

<dependency>
	<groupId>com.google</groupId>
	<artifactId>gdata-client</artifactId>
	<version>1.0</version>
	<type>jar</type>
</dependency>

## Client meta
mvn install:install-file -Dfile=gdata-client-meta-1.0.jar -DgroupId=com.google -DartifactId=gdata-client-meta -Dversion=1.0 -Dpackaging=jar

<dependency>
	<groupId>com.google</groupId>
	<artifactId>gdata-client-meta</artifactId>
	<version>1.0</version>
	<type>jar</type>
</dependency>

## Collect
mvn install:install-file -Dfile=google-collect-1.0-rc1.jar -DgroupId=com.google -DartifactId=collect -Dversion=1.0-rc1 -Dpackaging=jar

<dependency>
	<groupId>com.google</groupId>
	<artifactId>collect</artifactId>
	<version>1.0-rc1</version>
	<type>jar</type>
</dependency>
