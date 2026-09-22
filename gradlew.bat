@rem
@rem Copyright 2015 the original authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  Gradle startup script for Windows
@rem
@rem ##########################################################################

@rem Attempt to set APP_HOME
@rem Resolve links: $0 may be a link
set "APP_HOME=%~dp0"
@rem Shell, exit codes, etc.
set "APP_Name=Gradle"
set "APP_BASE_NAME=%~n0"

@rem Determine the Java command to use to start the JVM.
if defined JAVA_HOME goto findJava
java_path=system
:checkJava
if not exist "%JAVA_HOME%\bin\java.exe" goto noJava
java_path="%JAVA_HOME%\bin\java.exe"
goto foundJava
:findJava
java_path=java
goto checkJava
:noJava
echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.
echo.
exit /b 1

:foundJava
@rem Check for the existence of the wrapper jar
set "CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar"

@rem Execute Gradle
"%java_path%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*

:end
@rem End of script
