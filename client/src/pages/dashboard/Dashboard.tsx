import '../../css/Dashboard.css';
import { MdPeopleOutline } from 'react-icons/md';
import { ModuleScoresChart } from '@/components/ModuleScoresChart';
import { UpcomingEventsCalendar } from '@/components/UpcomingEventsCalendar';
import { useGetAllQuizzes } from '@/api/queries/useQuiz.ts';

function Dashboard() {
  const { data: quiezzes } = useGetAllQuizzes();

  return (
    <div className="relative overflow-hidden sm:w-4/4">
      {quiezzes &&
        quiezzes.map(quiz => (
          <div
            key={quiz.id}
            className="card bg-white border rounded-xl flex-1 h-20 text-center flex items-top justify-center pt-1 px-2 gap-2"
          >
            <h1>{quiz.content}</h1>
            <MdPeopleOutline className="mt-1" />
          </div>
        ))}
      <div className="flex min-h-screen bg-white mt-4 sm:mt-0 w-full">
        <main className="w-full bg-white border p-4 gap-4 border-b">
          <div className="">
            <div className="flex flex-col sm:flex-row gap-4">
              <div className="card bg-white border rounded-xl flex-1 h-20 text-center flex items-top justify-center pt-1 px2 gap-2">
                <h1>Toplam Sınavlar</h1>
                <MdPeopleOutline className="mt-1" />
              </div>

              <div className="card bg-white border rounded-xl flex-1 h-20 text-center flex items-top justify-center pt-1 px-2 gap-2">
                <h1>Öğrenciler</h1>
                <div>
                  <MdPeopleOutline className="mt-1" />
                </div>
              </div>

              <div className="card bg-white border rounded-xl flex-1 h-20 text-center flex items-top justify-center pt-1 px-2 gap-2">
                <h1>Ortalama Puan</h1>
                <MdPeopleOutline className="mt-1" />
              </div>

              <div className="card bg-white border rounded-xl flex-1 h-20 text-center flex items-top justify-center pt-1 px-2 gap-2">
                <h1>Modüller</h1>
                <MdPeopleOutline className="mt-1" />
              </div>
            </div>

            <div className="flex gap-4 mt-4 flex-col sm:flex-col">
              <div className="card bg-white border rounded mt-5 h-auto text-center flex flex-col items-top p-2">
                <h1 className="mb-2">Modüle Göre Ortalama Sınav Puanları</h1>

                <hr />

                <div className=" bg-gray-100 p-1 mt-3">
                  <ModuleScoresChart />
                </div>
              </div>
              <div className="card bg-white border rounded mt-5 h-auto text-center flex items-top justify-center p-2">
                <h1>Son Etkinlikler</h1>
              </div>

              <div className="card bg-white border rounded mt-5 h-auto text-center flex flex-col items-top justify-center p-0.5">
                <h1>Yaklaşan Etkinlikler</h1>
                <UpcomingEventsCalendar />
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
}

export default Dashboard;
