# enter your code here to solve the housing assignment
# voer hier je code in om de huisvestingsvraag op te lossen
from abc import ABC, ABCMeta, abstractmethod
import re
class Person:
    def __init__(self, id, name, is_a_student):
        if not Person.is_valid_name(name):
            raise ValueError("name must consist of 2 parts with a space")
        self.id = id
        self.__name = name
        self.is_a_student = is_a_student
    
    @property
    def name(self):
        return self.__name
    
    @name.setter
    def name(self, name):
        if not Person.is_valid_name(name):
            raise ValueError
        self.__name = name
    
    @staticmethod
    def is_valid_name(name):
        return re.search(r"[a-zA-Z]+ [a-zA-Z]+", name)

class Residence(ABC):
    def __init__(self, address, area, number_of_rooms):
        self.address = address
        self.area = area
        self.number_of_rooms = number_of_rooms
        self.__occupants = dict()
        self.__maximum_occupants = min(area//20, number_of_rooms*2)
    
    @property
    def resident_names(self):
        return [person.name for person in self.__occupants.values()]
    
    @property
    def maximum_occupants(self):
        return self.__maximum_occupants

    @abstractmethod
    def calculate_value(self):
        ...
    @property
    def number_of_occupants(self):
        return self.__occupants
    
    def register_resident(self, person):
        if person not in self.__occupants:
            if self.__maximum_occupants > self.__occupants:
                self.__occupants[person.id] = person
                self.__occupants+=1
            else:
                raise RuntimeError("residence is full")
        
    def unregister_resident(self, id):
        if id in self.__occupants.keys():
            del self.__occupants[id]
            self.__occupants-=1

class Villa(Residence):
    def __init__(self, address, area, number_of_rooms, garage_capacity):
        super().__init__(address, area, number_of_rooms)
        self.garage_capacity = garage_capacity
    
    def calculate_value(self):
        return (25000*self.number_of_rooms)+(2100*self.area)+(10000*self.garage_capaciteit)

class StudentKot(Residence):
    def __init__(self, address, area):
        super().__init__(address, area, 1)
        
    def register_resident(self, person):
        if not person.is_a_student:
            raise RuntimeError("person moet student zijn")
        return super().register_resident(person)
    
    def calculate_value(self):
        return 150000 + (750*self.area)
