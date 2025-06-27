import React, { useState } from 'react';

const Quizzes = () => {
  interface Lesson {
    id: string;
    name: string;
  }

  const lessons: Lesson[] = [
    { id: '1', name: 'Web Geliştirme' },
    { id: '2', name: 'Veritabanı Yönetimi' },
    { id: '3', name: 'Siber Güvenlik' },
    { id: '4', name: 'Yapay Zeka' },
    { id: '5', name: 'Bilgisayar Ağları' },
  ];

  const [selectedLesson, setSelectedLesson] = useState<Lesson | null>(null);

  return (
    <div className="flex flex-col gap-4">
      <h1 className="text-center">Ders Seçimi</h1>

      <hr />

      {lessons.map(lesson => (
        <button
          className="rounded-lg"
          key={lesson.id}
          onClick={() => setSelectedLesson(lesson)}
          style={{
            margin: '10px',
            padding: '10px',
            backgroundColor: selectedLesson?.id === lesson.id ? 'lightblue' : 'white',
          }}
        >
          {lesson.name}
        </button>
      ))}
    </div>
  );
};

export default Quizzes;
