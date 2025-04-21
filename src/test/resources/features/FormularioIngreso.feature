Feature: Llenado de furmulario de Ingreso de denuncia

  Background: Prueba
    Given El usuario navega hasta la pagina del formulario

  @smoke
  Scenario: Llenar formulario correctamente
    When El usuario llena el formulario correctamente
    Then El formulario se envia correctamente y se vi