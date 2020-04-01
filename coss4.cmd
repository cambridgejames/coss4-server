@echo off
SETLOCAL EnableDelayedExpansion
for /F "tokens=1,2 delims=#" %%a in ('"prompt #$H#$E# & echo on & for %%b in (1) do rem"') do (
  set "DEL=%%a"
)

:: read for args
:start
if "%1"=="" (
  goto help
) else if "%1"=="-h" (
  goto help
) else if "%1"=="--help" (
  goto help
) else if "%1"=="-v" (
  goto version
) else if "%1"=="--version" (
  goto version
) else if "%1"=="-b" (
  goto build
) else if "%1"=="--build" (
  goto build
) else if "%1"=="--init" (
  goto init
) else if "%1"=="-c" (
  goto clean
) else if "%1"=="--clean" (
  goto clean
) else if "%1"=="-i" (
  goto install
) else if "%1"=="--install" (
  goto install
) else if "%1"=="-t" (
  goto test
) else if "%1"=="--test" (
  goto test
) else (
  goto help
)

:help
type help\build-tools-help.txt
goto :eof

:build
mvn clean package
goto :eof

:init
if "%2"=="MySQL" (
    mysql -hlocalhost -ucoss4admin -pMysqlCoss4admin < sql\*.sql
) else (
    call :color_text 07 "["
    call :color_text 04 "ERROR"
    call :color_text 07 "] The profile associated with the specified database type could not be found."
)
goto :eof

:install
mvn clean install
goto :eof

:test
mvn test
goto :eof

:version
type help\version.txt
goto :eof

:color_text
echo off
<nul set /p ".=%DEL%" > "%~2"
findstr /v /a:%1 /R "^$" "%~2" nul
del "%~2" > nul 2>&1
goto :eof
