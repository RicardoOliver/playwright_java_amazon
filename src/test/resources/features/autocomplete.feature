# language: pt
Funcionalidade: Sugestões de Pesquisa (Autocomplete)
  Como um usuário da Amazon
  Eu quero receber sugestões enquanto digito na barra de pesquisa
  Para encontrar produtos mais rapidamente

  Contexto:
    Dado o usuário está na página inicial da Amazon

  @positivo @Autocomplete @Autocompletepositivo @regressivos
  Cenário: Exibir sugestões de pesquisa ao digitar um termo válido
    Quando o usuário digita "celular" na barra de pesquisa
    Então o sistema deve exibir sugestões de pesquisa
    E as sugestões devem estar relacionadas ao termo "celular"

  @positivo @Autocomplete @Autocompletepositivo @regressivos
  Cenário: Exibir sugestões para diferentes categorias de produtos
    Quando o usuário digita "notebook" na barra de pesquisa
    Então o sistema deve exibir sugestões de pesquisa
    E as sugestões devem estar relacionadas ao termo "notebook"

  @positivo @Autocomplete @Autocompletepositivo @regressivos
  Cenário: Exibir sugestões para termos de marca
    Quando o usuário digita "samsung" na barra de pesquisa
    Então o sistema deve exibir sugestões de pesquisa
    E as sugestões devem estar relacionadas ao termo "samsung"

  @positivo @Autocomplete @Autocompletepositivo @regressivos
  Esquema do Cenário: Exibir sugestões para diferentes termos de pesquisa
    Quando o usuário digita "<termo>" na barra de pesquisa
    Então o sistema deve exibir sugestões de pesquisa
    E as sugestões devem estar relacionadas ao termo "<termo>"

    Exemplos:
      | termo          |
      | fone de ouvido |
      | livro          |
      | smartwatch     |
      | tv             |

  @negativo @Autocomplete @Autocompletepositivo @regressivos
  Cenário: Não exibir sugestões para termos muito curtos
    Quando o usuário digita "a" na barra de pesquisa
    Então o sistema não deve exibir sugestões de pesquisa

  @negativo @Autocomplete @Autocompletenegativo @regressivos
  Cenário: Não exibir sugestões quando a barra de pesquisa está vazia
    Quando o usuário digita "" na barra de pesquisa
    Então o sistema não deve exibir sugestões de pesquisa

  @negativo @Autocomplete @Autocompletenegativo @regressivos
  Cenário: Lidar com caracteres especiais na pesquisa
    Quando o usuário digita "!@#$%" na barra de pesquisa
    Então o sistema não deve exibir sugestões de pesquisa

  @negativo @Autocomplete @Autocompletenegativo @regressivos
  Cenário: Lidar com termos de pesquisa muito longos
    Quando o usuário digita um termo de pesquisa inválido na barra de pesquisa
    Então o sistema não deve exibir sugestões de pesquisa

 

@positivo @Autocomplete @Autocompletepositivo
  Cenário: Submeter pesquisa a partir das sugestões
    Quando o usuário digita "celular" na barra de pesquisa
    E o sistema deve exibir sugestões de pesquisa
    E o usuário submete a pesquisa
    Então a página deve ser carregada com sucesso
