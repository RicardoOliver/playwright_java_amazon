FROM maven:3.9.5-eclipse-temurin-11 AS build

# Instalar dependências necessárias para o Playwright
RUN apt-get update && apt-get install -y \
    libglib2.0-0 \
    libnss3 \
    libnspr4 \
    libatk1.0-0 \
    libatk-bridge2.0-0 \
    libcups2 \
    libdrm2 \
    libdbus-1-3 \
    libxcb1 \
    libxkbcommon0 \
    libx11-6 \
    libxcomposite1 \
    libxdamage1 \
    libxext6 \
    libxfixes3 \
    libxrandr2 \
    libgbm1 \
    libpango-1.0-0 \
    libcairo2 \
    libasound2 \
    libatspi2.0-0 \
    libwayland-client0 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copiar arquivos de configuração do Maven primeiro para aproveitar o cache
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar o código-fonte
COPY src ./src

# Instalar os navegadores do Playwright
RUN mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install --with-deps"

# Compilar o projeto
RUN mvn clean compile

# Imagem final
FROM maven:3.9.5-eclipse-temurin-11

# Instalar dependências necessárias para o Playwright
RUN apt-get update && apt-get install -y \
    libglib2.0-0 \
    libnss3 \
    libnspr4 \
    libatk1.0-0 \
    libatk-bridge2.0-0 \
    libcups2 \
    libdrm2 \
    libdbus-1-3 \
    libxcb1 \
    libxkbcommon0 \
    libx11-6 \
    libxcomposite1 \
    libxdamage1 \
    libxext6 \
    libxfixes3 \
    libxrandr2 \
    libgbm1 \
    libpango-1.0-0 \
    libcairo2 \
    libasound2 \
    libatspi2.0-0 \
    libwayland-client0 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copiar o projeto compilado
COPY --from=build /app /app
COPY --from=build /root/.cache/ms-playwright /root/.cache/ms-playwright

# Comando para executar os testes
ENTRYPOINT ["mvn", "test"]
