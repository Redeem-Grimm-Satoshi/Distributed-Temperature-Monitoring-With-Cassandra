import { Wifi, WifiOff } from "lucide-react";

type NodeCardProps = {
  name: string;
  role: string;
  ip: string;
  temperature?: number; // optional for Laptop
  online: boolean;
};

export default function NodeCard({ name, role, ip, temperature, online }: NodeCardProps) {
  return (
    <div className="p-6 rounded-2xl shadow-md bg-white flex flex-col gap-3 border border-gray-100">
      <div className="flex items-center justify-between">
        <h2 className="text-xl font-semibold text-gray-800">{name}</h2>
        {online ? (
          <Wifi className="text-green-500" size={22} />
        ) : (
          <WifiOff className="text-red-500" size={22} />
        )}
      </div>
      <p className="text-gray-600">Role: {role}</p>
      <p className="text-gray-600">IP: {ip}</p>

      {temperature !== undefined && (
        <p className="text-lg font-bold text-red-700">🌡 {temperature.toFixed(1)}°C</p>
      )}

      <span
        className={`inline-block px-3 py-1 rounded-full text-sm font-medium ${
          online ? "bg-green-100 text-green-700" : "bg-red-100 text-red-700"
        }`}
      >
        {online ? "Online" : "Offline"}
      </span>
    </div>
  );
}
