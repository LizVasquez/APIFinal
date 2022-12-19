Feature: Item

  Scenario: Como usuario
    Quiero crear un item

    Given tengo acceso a Todo.ly
    When envio una peticion POST al url https://todo.ly/api/items.json con json
    """
    {
      "Content":"TestItem"

    }
    """
    Then espero el codigo de respuesta 200


    And tengo el Id y lo guardo en ID_ITEM
    When envio una peticion PUT al url https://todo.ly/api/items/ID_ITEM.json con json

    """
     {
      "Content":"TestItem""

    }
    """


    When envio una peticion GET al url https://todo.ly/api/items/ID_ITEM.json con json
    """
    """

    Then espero el codigo de respuesta 200

    When envio una peticion DELETE al url https://todo.ly/api/items/ID_ITEM.json con json
    """
    """
    Then espero el codigo de respuesta 200


