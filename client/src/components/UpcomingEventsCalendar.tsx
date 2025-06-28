import { useState } from 'react';
import { DayPicker } from 'react-day-picker';
import 'react-day-picker/dist/style.css';
import { format } from 'date-fns';
import { tr } from 'date-fns/locale/tr';

const highlightedDays = [new Date(2025, 5, 11), new Date(2025, 5, 19), new Date(2025, 5, 22)];

const events = [
  {
    date: new Date(2025, 5, 22),
    title: 'Test - COG',
    time: '22 Haziran, 14:00 - 16:00',
  },
  {
    date: new Date(2025, 5, 19),
    title: 'Sınav - MAT',
    time: '19 Haziran, 10:00 - 10:30',
  },
  {
    date: new Date(2025, 5, 11),
    title: 'Ara Sınav - TAR',
    time: '11 Haziran, 13:00 - 15:00',
  },
];

export const UpcomingEventsCalendar = () => {
  const [selected, setSelected] = useState<Date | undefined>();

  const selectedEvents = selected
    ? events.filter(event => format(event.date, 'yyyy-MM-dd') === format(selected, 'yyyy-MM-dd'))
    : events;

  return (
    <div className="bg-white p-4 rounded-lg shadow w-full flex flex-col items-center">
      <h2 className="text-lg font-semibold mb-1">Yaklaşan Etkinlikler</h2>
      <p className="text-sm text-gray-500 mb-3">Ocak - Haziran 2024</p>

      <div className="flex flex-row items-center gap-5">
        <DayPicker
          mode="single"
          selected={selected}
          onSelect={setSelected}
          showOutsideDays
          month={new Date(2025, 5)}
          modifiers={{ highlighted: highlightedDays }}
          modifiersStyles={{
            highlighted: {
              backgroundColor: '#06b6d4',
              color: 'white',
              borderRadius: '8px',
            },
          }}
          locale={tr}
        />

        <div className="mt-4">
          {selectedEvents.length > 0 ? (
            selectedEvents.map((event, i) => (
              <div key={i} className="text-sm text-gray-700 mb-2">
                <strong>{event.title}</strong>
                <br />
                {event.time}
              </div>
            ))
          ) : (
            <p className="text-sm text-gray-500">Etkinlik bulunamadı.</p>
          )}
        </div>
      </div>
    </div>
  );
};
