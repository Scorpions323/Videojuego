@echo off

start cmd /k java -cp target/classes es.etg.dam.server.Servidor

timeout /t 2 >nul

start cmd /k java -cp target/classes es.etg.dam.client.Cliente Juan
start cmd /k java -cp target/classes es.etg.dam.client.Cliente Ana

pause