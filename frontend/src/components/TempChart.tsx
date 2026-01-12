"use client";

import {
  LineChart,
  Line,
  AreaChart,
  Area,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";

// Shape of the chart data expected by Recharts.
type ChartData = {
  time: string;
  node1: number | null;
  node2: number | null;
};

// Props allow switching between multiple chart presentations.
type TempChartProps = {
  data: ChartData[];
  // Select the chart rendering style.
  style?: "line" | "area" | "minimal";
};

export default function TempChart({ data, style = "line" }: TempChartProps) {
  if (style === "area") {
    // Area chart with gradient fills for each node series.
    return (
      <div className="p-6 bg-white rounded-2xl shadow-lg border border-gray-100">
        <h2 className="text-2xl font-semibold text-gray-800 mb-6">
          📊 Temperature Trend (Area Style)
        </h2>
        <ResponsiveContainer width="100%" height={350}>
          <AreaChart data={data}>
            {/* Gradient definitions referenced by the area fills. */}
            <defs>
              <linearGradient id="colorNode1" x1="0" y1="0" x2="0" y2="1">
                <stop offset="5%" stopColor="#ef4444" stopOpacity={0.8} />
                <stop offset="95%" stopColor="#ef4444" stopOpacity={0} />
              </linearGradient>
              <linearGradient id="colorNode2" x1="0" y1="0" x2="0" y2="1">
                <stop offset="5%" stopColor="#3b82f6" stopOpacity={0.8} />
                <stop offset="95%" stopColor="#3b82f6" stopOpacity={0} />
              </linearGradient>
            </defs>
            <CartesianGrid strokeDasharray="3 3" stroke="#e5e7eb" />
            <XAxis dataKey="time" stroke="#374151" />
            <YAxis stroke="#374151" />
            <Tooltip />
            <Legend />
            <Area type="monotone" dataKey="node1" stroke="#ef4444" fill="url(#colorNode1)" name="Node 1" />
            <Area type="monotone" dataKey="node2" stroke="#3b82f6" fill="url(#colorNode2)" name="Node 2" />
          </AreaChart>
        </ResponsiveContainer>
      </div>
    );
  }

  if (style === "minimal") {
    // Minimalist sparkline style for compact displays
    return (
      <div className="p-6 bg-white rounded-2xl shadow-lg border border-gray-100">
        <h2 className="text-2xl font-semibold text-gray-800 mb-6">
          📊 Temperature Trend (Minimal)
        </h2>
        <ResponsiveContainer width="100%" height={200}>
          <LineChart data={data}>
            <XAxis dataKey="time" hide />
            <YAxis hide />
            <Tooltip />
            <Line type="monotone" dataKey="node1" stroke="#ef4444" strokeWidth={2} dot={false} name="Node 1" />
            <Line type="monotone" dataKey="node2" stroke="#3b82f6" strokeWidth={2} dot={false} name="Node 2" />
          </LineChart>
        </ResponsiveContainer>
      </div>
    );
  }


  // Default: classic line chart with axes and legend.
  return (
    <div className="p-6 bg-white rounded-2xl shadow-lg border border-gray-100">
      <h2 className="text-2xl font-semibold text-gray-800 mb-6">
        📊 Temperature Trend (Line Style)
      </h2>
      <ResponsiveContainer width="100%" height={350}>
        <LineChart data={data}>
          <CartesianGrid strokeDasharray="3 3" stroke="#e5e7eb" />
          <XAxis dataKey="time" stroke="#374151" />
          <YAxis stroke="#374151" />
          <Tooltip />
          <Legend />
          <Line type="monotone" dataKey="node1" stroke="#ef4444" strokeWidth={3} dot={false} name="Node 1" />
          <Line type="monotone" dataKey="node2" stroke="#3b82f6" strokeWidth={3} dot={false} name="Node 2" />
        </LineChart>
      </ResponsiveContainer>
    </div>
  );
}
