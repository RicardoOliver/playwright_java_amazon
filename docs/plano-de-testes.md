# Plano de Testes - Amazon.com.br

## 1. Introdução

### 1.1 Objetivo
Este plano de testes descreve a estratégia, escopo, abordagem e recursos necessários para testar a página inicial do site Amazon.com.br, com foco nas funcionalidades de sugestões de pesquisa (autocomplete), menu de navegação e desempenho de carregamento.

### 1.2 Documentos Relacionados
- Especificação de requisitos
- Arquivos de feature do Cucumber
- Documentação da API do Playwright
- Documentação do Allure Reports

## 2. Escopo do Teste

### 2.1 Itens a Serem Testados
- Funcionalidade de sugestões de pesquisa (autocomplete)
- Menu de navegação e responsividade
- Desempenho de carregamento da página

### 2.2 Itens Fora do Escopo
- Funcionalidades de login e cadastro
- Processo de compra
- Páginas de produto
- Carrinho de compras
- Checkout e pagamento

## 3. Estratégia de Teste

### 3.1 Tipos de Teste
- **Testes Funcionais**: Verificar se as funcionalidades atendem aos requisitos
- **Testes de Responsividade**: Garantir que a interface funcione em diferentes dispositivos
- **Testes de Desempenho**: Avaliar o tempo de carregamento e métricas de performance
- **Testes de Regressão**: Garantir que novas mudanças não afetem funcionalidades existentes

### 3.2 Abordagem de Teste
- Desenvolvimento orientado por comportamento (BDD) usando Cucumber
- Automação de testes com Playwright
- Arquitetura Page Object Model (POM)
- Execução em múltiplos navegadores (Chromium, Firefox, WebKit)
- Testes em diferentes tamanhos de tela para verificar responsividade

### 3.3 Critérios de Entrada
- Ambiente de teste configurado
- Navegadores do Playwright instalados
- Acesso ao site Amazon.com.br disponível
- Cenários de teste definidos e implementados

### 3.4 Critérios de Saída
- Todos os testes executados
- Taxa de sucesso de pelo menos 90%
- Todos os defeitos críticos e de alta prioridade resolvidos
- Relatórios de teste gerados e analisados

## 4. Ambiente de Teste

### 4.1 Hardware
- Computadores com pelo menos 8GB de RAM e processador i5 ou equivalente

### 4.2 Software
- Sistema Operacional: Windows 10/11, macOS, Linux
- Java 11+
- Maven 3.6+
- Navegadores: Chromium, Firefox, WebKit (via Playwright)
- Docker (opcional para execução em contêineres)

### 4.3 Ferramentas
- Playwright para automação de navegadores
- Cucumber para BDD
- JUnit para execução de testes
- Allure Reports para relatórios
- GitHub Actions para integração contínua

## 5. Cronograma

| Atividade | Duração | Data de Início | Data de Término |
|-----------|---------|----------------|-----------------|
| Planejamento de Teste | 1 semana | 01/05/2025 | 07/05/2025 |
| Configuração do Ambiente | 2 dias | 08/05/2025 | 09/05/2025 |
| Implementação dos Testes | 2 semanas | 10/05/2025 | 24/05/2025 |
| Execução dos Testes | 1 semana | 25/05/2025 | 31/05/2025 |
| Análise e Relatório | 3 dias | 01/06/2025 | 03/06/2025 |

## 6. Recursos

### 6.1 Equipe
- 1 Gerente de Teste
- 2 Engenheiros de Automação de Teste
- 1 Analista de Qualidade

### 6.2 Infraestrutura
- Ambiente de desenvolvimento local
- Ambiente de integração contínua (GitHub Actions)
- Repositório de código (GitHub)

## 7. Riscos e Mitigações

| Risco | Probabilidade | Impacto | Mitigação |
|-------|--------------|---------|-----------|
| Mudanças na estrutura do site | Alta | Alto | Implementar seletores robustos e múltiplas estratégias de localização |
| Bloqueio por mecanismos anti-bot | Média | Alto | Simular comportamento humano, usar timeouts adequados, implementar detecção de captcha |
| Problemas de conectividade | Média | Médio | Implementar retentativas e timeouts adequados |
| Diferenças entre navegadores | Alta | Médio | Testar em múltiplos navegadores e implementar tratamento específico quando necessário |
| Falsos positivos/negativos | Média | Médio | Implementar logs detalhados e capturas de tela para análise |

## 8. Casos de Teste

### 8.1 Sugestões de Pesquisa (Autocomplete)

#### 8.1.1 Cenários Positivos
1. **Exibir sugestões ao digitar um termo válido**
   - Dado que o usuário está na página inicial da Amazon
   - Quando o usuário digita "celular" na barra de pesquisa
   - Então o sistema deve exibir sugestões de pesquisa
   - E as sugestões devem estar relacionadas ao termo "celular"

2. **Exibir sugestões para diferentes categorias de produtos**
   - Dado que o usuário está na página inicial da Amazon
   - Quando o usuário digita "notebook" na barra de pesquisa
   - Então o sistema deve exibir sugestões de pesquisa
   - E as sugestões devem estar relacionadas ao termo "notebook"

3. **Exibir sugestões para termos de marca**
   - Dado que o usuário está na página inicial da Amazon
   - Quando o usuário digita "samsung" na barra de pesquisa
   - Então o sistema deve exibir sugestões de pesquisa
   - E as sugestões devem estar relacionadas ao termo "samsung"

4. **Submeter pesquisa a partir das sugestões**
   - Dado que o usuário está na página inicial da Amazon
   - Quando o usuário digita "celular" na barra de pesquisa
   - E o sistema deve exibir sugestões de pesquisa
   - E o usuário submete a pesquisa
   - Então a página deve ser carregada com sucesso

#### 8.1.2 Cenários Negativos
1. **Não exibir sugestões para termos muito curtos**
   - Dado que o usuário está na página inicial da Amazon
   - Quando o usuário digita "a" na barra de pesquisa
   - Então o sistema não deve exibir sugestões de pesquisa

2. **Não exibir sugestões quando a barra de pesquisa está vazia**
   - Dado que o usuário está na página inicial da Amazon
   - Quando o usuário digita "" na barra de pesquisa
   - Então o sistema não deve exibir sugestões de pesquisa

3. **Lidar com caracteres especiais na pesquisa**
   - Dado que o usuário está na página inicial da Amazon
   - Quando o usuário digita "!@#$%" na barra de pesquisa
   - Então o sistema não deve exibir sugestões de pesquisa

4. **Lidar com termos de pesquisa muito longos**
   - Dado que o usuário está na página inicial da Amazon
   - Quando o usuário digita um termo de pesquisa inválido na barra de pesquisa
   - Então o sistema não deve exibir sugestões de pesquisa

### 8.2 Menu de Navegação

#### 8.2.1 Cenários Positivos
1. **Abrir o menu hamburger**
   - Dado que o usuário está na página inicial da Amazon
   - Quando o usuário clica no menu hamburger
   - Então o menu principal deve ser exibido
   - E o menu deve conter categorias de departamentos

2. **Menu responsivo em diferentes tamanhos de tela**
   - Dado que o usuário está na página inicial da Amazon
   - Então o menu deve ser responsivo em diferentes tamanhos de tela

3. **Exibir links de departamentos no topo da página em desktop**
   - Dado que o usuário está na página inicial da Amazon
   - Quando o usuário redimensiona a janela para 1280x800
   - Então os links de departamentos devem ser exibidos no topo da página

4. **Exibir menu hamburger em dispositivos móveis**
   - Dado que o usuário está na página inicial da Amazon
   - Quando o usuário redimensiona a janela para 375x667
   - Então o menu hamburger deve ser visível

#### 8.2.2 Cenários Negativos
1. **Verificar comportamento do menu em tamanho de tela extremamente pequeno**
   - Dado que o usuário está na página inicial da Amazon
   - Quando o usuário redimensiona a janela para 240x320
   - Então a página deve ser carregada com sucesso

### 8.3 Carregamento e Desempenho

#### 8.3.1 Cenários Positivos
1. **Carregar a página inicial em tempo aceitável**
   - Dado que o usuário está na página inicial da Amazon
   - Então a página deve carregar dentro do tempo aceitável
   - E as métricas de desempenho devem ser coletadas

2. **Carregar a página em diferentes navegadores**
   - Dado que o usuário está na página inicial da Amazon
   - Então a página deve carregar em menos de 5.0 segundos
   - E as métricas de desempenho devem ser coletadas

3. **Verificar desempenho em diferentes tamanhos de tela**
   - Dado que o usuário está na página inicial da Amazon
   - Quando o usuário redimensiona a janela para <largura>x<altura>
   - Então a página deve carregar em menos de 5.0 segundos
   - E as métricas de desempenho devem ser coletadas

#### 8.3.2 Cenários Negativos
1. **Verificar desempenho com conexão lenta**
   - Dado que o usuário está na página inicial da Amazon
   - Então a página deve carregar dentro do tempo aceitável
   - E as métricas de desempenho devem ser coletadas

## 9. Métricas e Relatórios

### 9.1 Métricas de Teste
- Número total de testes
- Taxa de sucesso/falha
- Tempo médio de execução
- Cobertura de funcionalidades
- Número de defeitos encontrados por severidade

### 9.2 Relatórios
- Relatório Allure detalhado
- Relatório Cucumber HTML
- Capturas de tela de falhas
- Logs de execução
- Métricas de desempenho

## 10. Aprovações

| Nome | Cargo | Assinatura | Data |
|------|-------|------------|------|
| [Nome do Gerente de Projeto] | Gerente de Projeto | _____________ | ___/___/___ |
| [Nome do Gerente de QA] | Gerente de QA | _____________ | ___/___/___ |
| [Nome do Líder Técnico] | Líder Técnico | _____________ | ___/___/___ |

