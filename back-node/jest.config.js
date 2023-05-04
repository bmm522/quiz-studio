module.exports = {
    moduleFileExtensions: ["js", "json", "ts"],
    testPathIgnorePatterns: ["<rootDir>/node_modules/"],
    testMatch: [
        "<rootDir>\\test\\unit\\mapper\\*.test.(ts|js)",
        // "<rootDir>\\test\\*.test.(ts|js)",
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