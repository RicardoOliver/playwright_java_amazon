#!/bin/bash
echo "Executando testes com WebKit visível..."
mvn test -Dbrowser.headless=false -Dbrowser.type=webkit
echo "Testes concluídos!"
