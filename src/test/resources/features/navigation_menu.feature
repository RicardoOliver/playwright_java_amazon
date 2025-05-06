# language: pt
Funcionalidade: Menu de Navegação
  Como um usuário da Amazon
  Eu quero navegar pelo menu de categorias
  Para encontrar produtos por departamento

  Contexto:
    Dado o usuário está na página inicial da Amazon

  @positivo @navegar @navegarPositivo @regressivos
  Cenário: Abrir o menu hamburger
    Quando o usuário clica no menu hamburger
    Então o menu principal deve ser exibido
    E o menu deve conter categorias de departamentos

  @positivo @navegar  @navegarPositivo @regressivos
  Cenário: Menu responsivo em diferentes tamanhos de tela
    Então o menu deve ser responsivo em diferentes tamanhos de tela

  @positivo @navegar  @navegarPositivo @regressivos
  Cenário: Exibir links de departamentos no topo da página em desktop
    Quando o usuário redimensiona a janela para 1280x800
    Então os links de departamentos devem ser exibidos no topo da página

  @positivo @navegar @navegarPositivo @regressivos
  Cenário: Exibir menu hamburger em dispositivos móveis
    Quando o usuário redimensiona a janela para 375x667
    Então o menu hamburger deve ser visível

  @positivo @navegar @navegarPositivo @regressivos
  Esquema do Cenário: Verificar responsividade em diferentes tamanhos de tela
    Quando o usuário redimensiona a janela para <largura>x<altura>
    Então a página deve ser carregada com sucesso

    Exemplos:
      | largura | altura |
      | 1920    | 1080   |
      | 1366    | 768    |
      | 768     | 1024   |
      | 414     | 896    |
      | 375     | 667    |
      | 320     | 568    |

  @negativo @navegar @navegarNegativo @regressivos
  Cenário: Verificar comportamento do menu em tamanho de tela extremamente pequeno
    Quando o usuário redimensiona a janela para 240x320
    Então a página deve ser carregada com sucesso
