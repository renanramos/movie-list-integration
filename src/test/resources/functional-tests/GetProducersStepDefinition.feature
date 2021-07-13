Feature: 
 Get producers

 @GetProducersFromServer
 Scenario: Verify producers with min and max interval
  Given I perform GET operation for </producers>
  Then the response is the producers