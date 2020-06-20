@echo off

if "%1"=="/?" (echo eventlog.bat ^<^<Base location for logs^>^> ^<^<Symantec Insalled location apart from base directory^>^>
echo for ex: eventlog.bat "f:\auditlogs" "c:\programdata\symantec\symantec endpoint protection"
echo Information^: Two folders will be created^, one for eventlog with current data time and another for Symanteclogs from the provided location
goto :endofbatch)
echo Start of event log creations for Application, Security, System, Setup
set sourcedir=%1
if "%sourcedir%"=="" set sourcedir=%localappdata%
set evtdir=%USERNAME%_AuditDND/%Date:~4,2%_%Date:~7,2%_%Date:~10,4%/Eventlog
IF exist "%sourcedir%/%evtdir%" (IF %ERRORLEVEL% EQU 0 (
       	wevtutil epl Application "%sourcedir%/%evtdir%"/%Date:~4,2%_%Date:~7,2%_%Date:~10,4%_%time:~0,2%_%time:~3,2%_%time:~6,2%_Application.evtx
	wevtutil epl Security "%sourcedir%/%evtdir%"/%Date:~4,2%_%Date:~7,2%_%Date:~10,4%_%time:~0,2%_%time:~3,2%_%time:~6,2%_Security.evtx
	wevtutil epl System "%sourcedir%/%evtdir%"/%Date:~4,2%_%Date:~7,2%_%Date:~10,4%_%time:~0,2%_%time:~3,2%_%time:~6,2%_System.evtx
    )) else (
mkdir "%sourcedir%/%evtdir%"

wevtutil epl Application "%sourcedir%/%evtdir%"/%Date:~4,2%_%Date:~7,2%_%Date:~10,4%_%time:~0,2%_%time:~3,2%_%time:~6,2%_Application.evtx
wevtutil epl Security "%sourcedir%/%evtdir%"/%Date:~4,2%_%Date:~7,2%_%Date:~10,4%_%time:~0,2%_%time:~3,2%_%time:~6,2%_Security.evtx
wevtutil epl System "%sourcedir%/%evtdir%"/%Date:~4,2%_%Date:~7,2%_%Date:~10,4%_%time:~0,2%_%time:~3,2%_%time:~6,2%_System.evtx
) 
echo End of event log creations for Application, Security, System, Setup 

echo Start of Symantec log creations
set symantecdir=%2/CurrentVersion/Data/Logs
if [%2]==[](set symantecdir="C:\ProgramData\Symantec\Symantec Endpoint Protection/CurrentVersion/Data/Logs")
set symantecclientdir=%USERNAME%_AuditDND/%Date:~4,2%_%Date:~7,2%_%Date:~10,4%/Symanteclog/%time:~0,2%_%time:~3,2%_%time:~6,2%
if not exist "%sourcedir%/%symantecclientdir%" (mkdir "%sourcedir%/%symantecclientdir%")
xcopy "%symantecdir%"\* "%sourcedir%/%symantecclientdir%"\* /s /e /Y /q > nul
echo End of Symantec log Collections

echo Start of move to Infinite location and delete these files
set eventsourcedirectory="%sourcedir%/%evtdir%"
set symantecsourcedirectory="%sourcedir%/%symantecclientdir%"
echo Your audit files are available in %eventsourcedirectory% and %symantecsourcedirectory%
echo End of move to Infinite location and delete these files

:endofbatch