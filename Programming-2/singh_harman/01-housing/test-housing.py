# Write your own tests for the housing.py file here.
# You must include the tests asked for in the assignment for full credit.
# You may add additional tests if you would like to test your code more thoroughly.
# Additional tests will not result in a higher grade.
# This file must be able to be run without error in order to receive credit for the required testing.
####
# Schrijf hier je eigen tests voor het housing.py bestand.
# Je moet de gevraagde tests in de opdracht opnemen voor volledige waardering.
# Je mag extra tests toevoegen als je je code grondiger wilt testen.
# Extra tests zullen niet leiden tot een hoger cijfer.
# Dit bestand moet zonder fouten uitgevoerd kunnen worden om punten te krijgen voor de vereiste testen.
import pytest
from housing import Villa, StudentKot


@pytest.mark.parametrize('address, area, number_of_rooms, garage_capacity, expected_max_occupants', [
    ('addres 1', 30, 1, 30, 1),
    ('addres 2', 40, 1, 30, 2),
    ('addres 3', 80, 1, 30, 2),
    ('addres 4', 80, 3, 30, 4),
    ('addres 5', 72, 3, 30, 3),
    ('studiolaan 6', 30, 1, 40, 1),
    ('studiolaan 7', 40, 1, 30, 2),
    ("appartementlaan", 200, 3, 300, 6)
])
def test_villa_residence(address, area, number_of_rooms, garage_capacity, expected_max_occupants):
    actual = Villa(address, area, number_of_rooms, garage_capacity)
    actual = actual.maximum_occupants
    assert actual == expected_max_occupants, f"actual: {actual}, expected: {expected_max_occupants}"

@pytest.mark.parametrize('address, area, number_of_rooms, garage_capacity', [
    ('addres 1', 19, 1, 30),
    ('addres 3', 21, 0, 30)
])
def test_villa_residence_false(address, area, number_of_rooms, garage_capacity):
    actual = Villa(address, area, number_of_rooms, garage_capacity)
    actual = actual.maximum_occupants
    assert actual == 0, f"actual: {actual}, expected: 0"

@pytest.mark.parametrize('address, area, expected', [
    ('studentlaan 1', 20, 1), 
    ('studentlaan 1', 30, 1), 
    ('studentlaan 1', 39, 1), 
    ('studentlaan 1', 40, 2), 
    ('studentlaan 1', 60, 2),
    ('studentlaan 1', 80, 2),
    ('studentlaan 1', 100, 2), 
    ('studentlaan 1', 300000, 2)
])
def test_studentKot_residence_true(address, area, expected):
    actual = StudentKot(address, area)
    actual = actual.maximum_occupants
    assert actual == expected, f"actual: {actual}, expected: {expected}"


@pytest.mark.parametrize('address, area, expected', [
    ('studentlaan 1', 19, 0)
])
def test_studentKot_residence_false(address, area, expected):
    actual = StudentKot(address, area)
    actual = actual.maximum_occupants
    assert actual == expected, f"actual: {actual}, expected: {expected}"