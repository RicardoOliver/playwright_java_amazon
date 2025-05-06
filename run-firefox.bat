@echo off
echo Executando testes com Firefox visível...
mvn test "-Dbrowser.headless=false" "-Dbrowser.type=firefox"
echo Testes concluídos!
pause
