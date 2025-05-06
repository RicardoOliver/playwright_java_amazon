@echo off
echo Executando testes com Chromium visível...
mvn test "-Dbrowser.headless=false" "-Dbrowser.type=chromium"
echo Testes concluídos!
pause
