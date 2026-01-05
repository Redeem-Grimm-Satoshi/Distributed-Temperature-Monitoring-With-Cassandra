"use client";

import { Laptop, Server, Database, Router } from "lucide-react";
import { motion } from "framer-motion";

export default function NetworkDiagram() {
  return (
    <div className="p-6 bg-white rounded-2xl shadow-md border border-gray-100 flex flex-col items-center gap-6">
      <h2 className="text-xl font-semibold text-gray-800">
        Network Topology
      </h2>

      <div className="flex flex-col items-center gap-6">
        {/* Laptop */}
        <motion.div
          className="flex flex-col items-center"
          animate={{ scale: [1, 1.05, 1] }}
          transition={{ repeat: Infinity, duration: 2 }}
        >
          <Laptop className="text-blue-600" size={40} />
          <p className="mt-2 text-gray-700 font-medium">
            Laptop (Frontend + Controller)
          </p>
          <span className="text-sm text-gray-500">192.168.1.50</span>
        </motion.div>

        {/* Connection to Router */}
        <motion.div
          className="w-0.5 h-10 bg-gradient-to-b from-blue-400 to-purple-400"
          animate={{ opacity: [0.4, 1, 0.4] }}
          transition={{ repeat: Infinity, duration: 1.5 }}
        />

        {/* Router */}
        <motion.div
          className="flex flex-col items-center"
          animate={{ scale: [1, 1.05, 1] }}
          transition={{ repeat: Infinity, duration: 2, delay: 0.5 }}
        >
          <Router className="text-purple-600" size={40} />
          <p className="mt-2 text-gray-700 font-medium">
            Router (Network + Internet)
          </p>
          <span className="text-sm text-gray-500">192.168.1.1</span>
        </motion.div>

        {/* Connection line */}
        <motion.div
          className="w-0.5 h-10 bg-gradient-to-b from-purple-400 to-green-400"
          animate={{ opacity: [0.4, 1, 0.4] }}
          transition={{ repeat: Infinity, duration: 1.5, delay: 0.8 }}
        />

        {/* Cluster with Cassandra */}
        <div className="flex flex-row gap-20">
          {/* Node 1 */}
          <motion.div
            className="flex flex-col items-center"
            animate={{ y: [0, -5, 0] }}
            transition={{ repeat: Infinity, duration: 2 }}
          >
            <Server className="text-green-600" size={40} />
            <Database className="text-gray-600 mt-2" size={28} />
            <p className="mt-2 text-gray-700 font-medium">Node 1 (Seed)</p>
            <span className="text-sm text-gray-500">192.168.1.101</span>
          </motion.div>

          {/* Node 2 */}
          <motion.div
            className="flex flex-col items-center"
            animate={{ y: [0, -5, 0] }}
            transition={{ repeat: Infinity, duration: 2, delay: 0.5 }}
          >
            <Server className="text-green-600" size={40} />
            <Database className="text-gray-600 mt-2" size={28} />
            <p className="mt-2 text-gray-700 font-medium">Node 2 (Peer)</p>
            <span className="text-sm text-gray-500">192.168.1.102</span>
          </motion.div>
        </div>
      </div>
    </div>
  );
}
