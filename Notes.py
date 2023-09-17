# Реализовать консольное приложение заметки, с сохранением, чтением,
# добавлением, редактированием и удалением заметок. Заметка должна
# содержать идентификатор, заголовок, тело заметки и дату/время создания или
# последнего изменения заметки. Сохранение заметок необходимо сделать в
# формате json или csv формат (разделение полей рекомендуется делать через
# точку с запятой). Реализацию пользовательского интерфейса студент может
# делать как ему удобнее, можно делать как параметры запуска программы
# (команда, данные), можно делать как запрос команды с консоли и
# последующим вводом данных, как-то ещё, на усмотрение студента.

import json
from datetime import datetime

file_path = "data.json"

class Note: 
    def __init__(self,id,header, text, date) -> None:
        self.id = id
        self.header = header
        self.text = text
        self.date = date
    
    # def __str__(self) -> str:
    #     return f"id: {self.id}\ndatetime: {self.date}\nHeader: {self.header}\ntext: {self.text}\n"
        
class NoteBook():
    def __init__(self, file_name) -> None:
        self.notes = []
        self.file_name = file_name
        self.load_notes()
        
    def load_notes(self):
        try:
            with open(self.file_name, 'r') as file:
                notes_data = json.load(file)
                for note_data in notes_data:
                    note = Note(
                        note_data['id'],
                        note_data['header'],
                        note_data['text'],
                        self.get_datetime_from_string(note_data['date'])
                    )
                    self.notes.append(note)
        except FileNotFoundError:
            print("Файл с заметками не найден. Будет создан новый файл.")
            
    def save_notes(self):
        notes_data = []
        for note in self.notes:
            note_data = {
                'id': note.id,
                'header': note.header,
                'text': note.text,
                'date': self.get_datetime_string(note.date)
            }
            notes_data.append(note_data)
        with open(self.file_name, 'w') as file:
            json.dump(notes_data, file, indent=4)
            
    def get_datetime_string(self, datetime_obj):
        return str(datetime_obj)

    def get_datetime_from_string(self, datetime_str):
        return datetime.fromisoformat(datetime_str)  
        
    def add_note(self):
        note_id = len(self.notes) + 1
        header = input("Введите заголовок: ")
        text = input("Введите текст: ")
        date = datetime.now()
        note = Note(note_id, header, text, date)
        self.notes.append(note)
        self.save_notes()
        print("Заметка успешно добавлена!")  
        
    def display_notes(self):
        print("\nСписок заметок:")
        for note in self.notes:
            print("ID:", note.id)
            print("Заголовок:", note.header)
            print("Содержание:", note.text)
            print("Дата/время создания:", note.date)
            
    def edit_note(self):
        id = int(input("Введите id заметки для редактирования: "))
        is_found = False
        for note in self.notes:
            if note.id == id:
                note.header = input("Введите новый заголовок: ")
                note.text = input("Введите новый текст: ")
                note.date = datetime.now()
                is_found = True
                break
        if(is_found): 
            print("Заметка успешно отредактирована!")
        else:
            print("Заметка с указанным id не найдена!")
            
    def delete_note(self):
        id = int(input("Введите id заметки для удаления: "))
        is_found = False
        for note in self.notes:
            if note.id == id:
                self.notes.remove(note)
                is_found = True
                break
        if(is_found): 
            print("Заметка успешно удалена!")
        else:
            print("Заметка с указанным id не найдена!")
                                
        
def main():
    notebook = NoteBook(file_path)
    while True:
        print("\nМеню:")
        print("1. Просмотреть заметки")
        print("2. Добавить заметку")
        print("3. Редактировать заметку")
        print("4. Удалить заметку")
        print("5. Выход")
        choice = input("Выберите действие: ")
        print("\n")
        if choice == '1':
            notebook.display_notes()
        elif choice == '2':
            notebook.add_note()
        elif choice == '3':
            notebook.edit_note()
        elif choice == '4':
            notebook.delete_note()
        elif choice == '5':
            break
        else:
            print("Некорректный выбор. Пожалуйста, выберите число от 1 до 5.")
            
main()