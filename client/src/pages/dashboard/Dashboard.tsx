import '../../css/Dashboard.css';

function Dashboard() {
  return (
    <div>
      <div className="flex min-h-screen bg-white mt-4 sm:mt-0">
        <main className="w-full bg-white border p-4 gap-4 border-b">
          <div>
            <div className="flex flex-col sm:flex-row gap-4">
              <div className="card bg-gray-300 flex-1 h-20 text-center flex items-center justify-center">
                1
              </div>
              <div className="card bg-gray-300 flex-1 h-20 text-center flex items-center justify-center">
                2
              </div>
            </div>
            <div className="card bg-gray-300 mt-5 h-30 text-center flex items-center justify-center">
              3
            </div>
            <div className="card bg-gray-300 mt-5 h-30 text-center flex items-center justify-center">
              4
            </div>

            <div className="card bg-gray-300 mt-5 h-12 text-center flex items-center justify-center">
              5
            </div>
          </div>
        </main>
      </div>
    </div>
  );
}

export default Dashboard;
