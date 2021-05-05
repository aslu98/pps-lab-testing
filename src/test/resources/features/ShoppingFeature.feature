Feature: Shopping

  Scenario: I want to pick a product during shopping
    Given An empty cart
    When I pick a product with name "tomato"
    Then My cart should contain 1 element

  Scenario: I want to remove an item from cart
    Given A cart with 3 items:
      | tomato | shoes | nail polish |
    When I remove the product "shoes"
    Then My cart should contain 2 items:
      | tomato | nail polish |