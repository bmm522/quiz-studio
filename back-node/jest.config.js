const path = require('path');
const rootDir = path.join(__dirname, '/');

module.exports = {
    moduleFileExtensions: ["js", "json", "ts"],
    testPathIgnorePatterns: ["<rootDir>/node_modules/"],
    testMatch: [
        // path.join(rootDir, "test/unit/mapper/*.test.(ts|js)"),
        // path.join(rootDir, "test/unit/repository/*.test.(ts|js)"),
        // path.join(rootDir, "test/unit/dto/*.test.(ts|js)"),
        // path.join(rootDir, "test/unit/jwt/*.test.(ts|js)"),
        // path.join(rootDir, "test/unit/service/*.test.(ts|js)"),
        path.join(rootDir, "test/e2e/*.test.(ts|js)"),

    ],
    transform: {
        "^.+\\.(t|j)s$": "ts-jest",
    },
    collectCoverageFrom: [
        "src/**/*.ts",
        "!src/app.ts",
        "!src/start.ts",
        "!src/**/entity/**",
    ],
    coverageDirectory: "coverage",
    testEnvironment: "node",
};