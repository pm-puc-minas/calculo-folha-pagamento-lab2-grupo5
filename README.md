[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=20461449&assignment_repo_type=AssignmentRepo)


***RF 6,7,8 - Calculo de Impostos***

**Para desenvolvermos os requisitos Calcular Desconto de INSS RF6,  Calcular FGTS RF7 e Calcular Desconto de IRRF RF8, desenvolvemos uma classe abstrata Imposto para definir um contrato de cálculo que é implementado de forma específica pelas classes INSS, IRRF e FGTS, permitindo o cálculo polimórfico de diferentes tributos sobre a folha de pagamento do funcionários, e relatórios.**

*A herança implementada permite que as subclasses (INSS, IRRF, FGTS) reutilizem a estrutura e o contrato definidos pela superclasse Imposto. Esta abordagem estabelece uma relação do tipo é um**(a classe INSS é um tipo de Imposto).*

*A classe Imposto abstrai o conceito de um cálculo de imposto, definindo um método calcularImposto sem fornecer uma implementação. A abstração esconde a complexidade dos cálculos específicos dentro de cada subclasse, garantindo o encasuplamento do método. (Quem utiliza as classes não precisa conhecer os detalhes de cada cálculo; basta invocar o método padrão calcularImposto).* 

*Graças ao polimorfismo, classes diferentes como INSS, IRRF podem ser tratadas de maneira uniforme através da superclasse Imposto. Isso permite, processar uma lista de diferentes impostos e calcular seus valores em loop, sem a necessidade de verificar o tipo específico de cada um, apenas com o método calcularImposto, inclusive facilita o acoplamento de novos métodos de possiveis novas tributações.*
