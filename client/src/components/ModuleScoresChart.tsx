import { BarChart, Bar, XAxis, YAxis, Tooltip, ResponsiveContainer } from 'recharts';

const data = [
  { name: 'MAT', score: 70 },
  { name: 'COG', score: 90 },
  { name: 'TAR', score: 80 },
  { name: 'EDB', score: 30 },
];

export const ModuleScoresChart = () => {
  return (
    <div className="w-full bg-white rounded-lg shadow p-1">
      <h2 className="text-lg font-semibold mb-1">Ortalama Sınav Puanları</h2>
      <p className="text-sm text-gray-500 mb-2">Ocak - Haziran 2025</p>

      <div className="w-full h-[250px] sm:h-[300px]">
        <ResponsiveContainer width="100%" height="100%">
          <BarChart data={data}>
            <XAxis dataKey="name" />
            <YAxis />
            <Tooltip />
            <Bar dataKey="score" fill="#00c49f" radius={[10, 10, 0, 0]} />
          </BarChart>
        </ResponsiveContainer>
      </div>

      <p className="text-sm mt-2 text-gray-700 font-medium">
        Bu ay <span className="text-green-500">5.2%</span> oranında artış 📈
      </p>
    </div>
  );
};
