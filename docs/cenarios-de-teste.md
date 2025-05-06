# Cenários de Teste - Amazon.com.br

## 1. Sugestões de Pesquisa (Autocomplete)

### 1.1 Cenários Positivos

#### 1.1.1 Exibir sugestões ao digitar um termo válido
**ID:** TC-001  
**Prioridade:** Alta  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Digitar "celular" na barra de pesquisa
**Resultado Esperado:**
- O sistema exibe sugestões de pesquisa
- As sugestões estão relacionadas ao termo "celular"

#### 1.1.2 Exibir sugestões para diferentes categorias de produtos
**ID:** TC-002  
**Prioridade:** Alta  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Digitar "notebook" na barra de pesquisa
**Resultado Esperado:**
- O sistema exibe sugestões de pesquisa
- As sugestões estão relacionadas ao termo "notebook"

#### 1.1.3 Exibir sugestões para termos de marca
**ID:** TC-003  
**Prioridade:** Alta  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Digitar "samsung" na barra de pesquisa
**Resultado Esperado:**
- O sistema exibe sugestões de pesquisa
- As sugestões estão relacionadas ao termo "samsung"

#### 1.1.4 Exibir sugestões para diferentes termos de pesquisa
**ID:** TC-004  
**Prioridade:** Média  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Digitar um dos seguintes termos na barra de pesquisa:
   - "fone de ouvido"
   - "livro"
   - "smartwatch"
   - "tv"
**Resultado Esperado:**
- O sistema exibe sugestões de pesquisa
- As sugestões estão relacionadas ao termo digitado

#### 1.1.5 Sugestões desaparecem ao limpar a barra de pesquisa
**ID:** TC-005  
**Prioridade:** Baixa  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Digitar "celular" na barra de pesquisa
2. Verificar que o sistema exibe sugestões de pesquisa
3. Limpar a barra de pesquisa
**Resultado Esperado:**
- O sistema não exibe mais sugestões de pesquisa

#### 1.1.6 Submeter pesquisa a partir das sugestões
**ID:** TC-006  
**Prioridade:** Alta  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Digitar "celular" na barra de pesquisa
2. Verificar que o sistema exibe sugestões de pesquisa
3. Submeter a pesquisa
**Resultado Esperado:**
- A página de resultados de pesquisa é carregada com sucesso

### 1.2 Cenários Negativos

#### 1.2.1 Não exibir sugestões para termos muito curtos
**ID:** TC-007  
**Prioridade:** Baixa  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Digitar "a" na barra de pesquisa
**Resultado Esperado:**
- O sistema não exibe sugestões de pesquisa

#### 1.2.2 Não exibir sugestões quando a barra de pesquisa está vazia
**ID:** TC-008  
**Prioridade:** Baixa  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Digitar "" (string vazia) na barra de pesquisa
**Resultado Esperado:**
- O sistema não exibe sugestões de pesquisa

#### 1.2.3 Lidar com caracteres especiais na pesquisa
**ID:** TC-009  
**Prioridade:** Baixa  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Digitar "!@#$%" na barra de pesquisa
**Resultado Esperado:**
- O sistema não exibe sugestões de pesquisa

#### 1.2.4 Lidar com termos de pesquisa muito longos
**ID:** TC-010  
**Prioridade:** Baixa  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Digitar um termo de pesquisa muito longo (mais de 256 caracteres)
**Resultado Esperado:**
- O sistema não exibe sugestões de pesquisa ou limita o número de caracteres

## 2. Menu de Navegação

### 2.1 Cenários Positivos

#### 2.1.1 Abrir o menu hamburger
**ID:** TC-011  
**Prioridade:** Alta  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Clicar no menu hamburger
**Resultado Esperado:**
- O menu principal é exibido
- O menu contém categorias de departamentos

#### 2.1.2 Menu responsivo em diferentes tamanhos de tela
**ID:** TC-012  
**Prioridade:** Alta  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Verificar o comportamento do menu em diferentes tamanhos de tela
**Resultado Esperado:**
- O menu se adapta corretamente a diferentes tamanhos de tela

#### 2.1.3 Exibir links de departamentos no topo da página em desktop
**ID:** TC-013  
**Prioridade:** Média  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Redimensionar a janela para 1280x800 (tamanho desktop)
**Resultado Esperado:**
- Os links de departamentos são exibidos no topo da página

#### 2.1.4 Exibir menu hamburger em dispositivos móveis
**ID:** TC-014  
**Prioridade:** Alta  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Redimensionar a janela para 375x667 (tamanho mobile)
**Resultado Esperado:**
- O menu hamburger é visível

#### 2.1.5 Verificar responsividade em diferentes tamanhos de tela
**ID:** TC-015  
**Prioridade:** Média  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Redimensionar a janela para os seguintes tamanhos:
   - 1920x1080
   - 1366x768
   - 768x1024
   - 414x896
   - 375x667
   - 320x568
**Resultado Esperado:**
- A página é carregada com sucesso em todos os tamanhos
- Os elementos se adaptam corretamente a cada tamanho

### 2.2 Cenários Negativos

#### 2.2.1 Verificar comportamento do menu em tamanho de tela extremamente pequeno
**ID:** TC-016  
**Prioridade:** Baixa  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Redimensionar a janela para 240x320 (tamanho extremamente pequeno)
**Resultado Esperado:**
- A página é carregada com sucesso
- O menu hamburger permanece acessível

## 3. Carregamento e Desempenho

### 3.1 Cenários Positivos

#### 3.1.1 Carregar a página inicial em tempo aceitável
**ID:** TC-017  
**Prioridade:** Alta  
**Pré-condições:** Usuário está navegando para a página inicial da Amazon  
**Passos:**
1. Acessar a página inicial da Amazon
**Resultado Esperado:**
- A página carrega dentro do tempo aceitável (menos de 10 segundos)
- As métricas de desempenho são coletadas

#### 3.1.2 Carregar a página em diferentes navegadores
**ID:** TC-018  
**Prioridade:** Média  
**Pré-condições:** Ambiente configurado com múltiplos navegadores  
**Passos:**
1. Acessar a página inicial da Amazon em diferentes navegadores (Chromium, Firefox, WebKit)
**Resultado Esperado:**
- A página carrega em menos de 5.0 segundos em cada navegador
- As métricas de desempenho são coletadas

#### 3.1.3 Verificar desempenho em diferentes tamanhos de tela
**ID:** TC-019  
**Prioridade:** Média  
**Pré-condições:** Usuário está na página inicial da Amazon  
**Passos:**
1. Redimensionar a janela para os seguintes tamanhos:
   - 1920x1080
   - 1366x768
   - 768x1024
   - 375x667
**Resultado Esperado:**
- A página carrega em menos de 5.0 segundos em cada tamanho de tela
- As métricas de desempenho são coletadas

### 3.2 Cenários Negativos

#### 3.2.1 Verificar desempenho com conexão lenta
**ID:** TC-020  
**Prioridade:** Baixa  
**Pré-condições:** Ambiente configurado para simular conexão lenta  
**Passos:**
1. Configurar o Playwright para simular conexão lenta
2. Acessar a página inicial da Amazon
**Resultado Esperado:**
- A página carrega dentro do tempo aceitável (considerando a conexão lenta)
- As métricas de desempenho são coletadas
- Não ocorrem erros de timeout

