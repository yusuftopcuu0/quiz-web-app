import '../../css/Dashboard.css';

function Dashboard() {
  return (
    <div>
      <div className="flex min-h-screen bg-white mt-4 sm:mt-0">
        <main className="w-full bg-white border p-4 gap-4 border-b">
          <div>
            <div className="flex flex-col sm:flex-row gap-4">
              <div className="card bg-white border rounded-xl flex-1 h-20 text-center flex items-center justify-center">
                Toplam Sınavlar
              </div>
              <div className="card bg-white border rounded-xl flex-1 h-20 text-center flex items-center justify-center">
                Öğrenciler
              </div>

              <div className="card bg-white border rounded-xl flex-1 h-20 text-center flex items-center justify-center">
                Ortalama Puan
              </div>

              <div className="card bg-white border rounded-xl flex-1 h-20 text-center flex items-center justify-center">
                Modüller
              </div>
            </div>

            <div className="flex gap-4 mt-4 flex-col sm:flex-row">
              <div className="card bg-white border rounded mt-5 h-60 text-center flex items-center justify-center p-2">
                <h1>Modüle Göre Ortalama Sınav Puanları</h1>
              </div>
              <div className="card bg-white border rounded mt-5 h-60 text-center flex items-center justify-center p-2">
                <h1>Son Etkinlikler</h1>
              </div>

              <div className="card bg-white border rounded mt-5 h-60 text-center flex items-center justify-center p-2">
                <h1>Yaklaşan Etkinlikler</h1>
              </div>
            </div>
          </div>
        </main>
      </div>
    </div>
  );
}

export default Dashboard;
