module.exports = function(grunt) {

  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    
    clean: ["src/main/webapp/build/js","src/main/webapp/build/css","src/main/webapp/build/views"],
    
    copy: {
    	 src: {
    		 files: [
    	         {expand: true, cwd: 'src/main/webapp/', src: ['src/main/webapp/views/*.{jsp.htm,html}'], dest: 'build/views'}
    	     ]
    	 }
    },
    
    uglify: {
      options: {
        banner: '/*! 成都蓝海飞鱼科技有限公司http://lhfeiyu.com   <%= pkg.name %>-<%= pkg.version %> - <%= grunt.template.today("yyyy-mm-dd") %> */\n'
      },
      build: {
        files: [{
                    expand:true,
                    cwd:'src/main/webapp/js',//js目录下
                    src:'**/*.js',//所有js文件
                    dest: 'src/main/webapp/build/js',//输出到此目录下
                    ext: '-<%= pkg.version %>.min.js'
                }]
      }
    },
    cssmin: {
	  options: {                                 // Target options 
	      advanced :false
	  },
      target: {
        files: [{
          expand: true,
          cwd: 'src/main/webapp/css',
          src: '**/*.css',
          dest: 'src/main/webapp/build/css',
          ext: '-<%= pkg.version %>.min.css'
        }]
      }
    },
    htmlmin: {                                     // Task 
      build: {                                      // Target 
        options: {                                 // Target options 
          removeComments: true,
          collapseWhitespace: true,
          keepClosingSlash:true,
          caseSensitive:true,
          preventAttributesEscaping:true
        },
        files: [{
          expand: true,
          cwd: 'src/main/webapp/views',
          src: ['**/*.{jsp,htm,html}'],
          dest: 'src/main/webapp/build/views'
        }]
      }
    },
    
//   usemin: { 
//       css: {
//    	   files:{src:['build/css/**/*.css']}
//       },
//       js:'build/**/*.js',
//       html:'build/**/*.{jsp,htm,html}',
//       options:{
//    	   assetsDirs:['build']
//       },
//       patterns:{
//    	   js
//       }
//   },
//  


  });

  grunt.loadNpmTasks('grunt-contrib-clean');
  grunt.loadNpmTasks('grunt-contrib-copy');
  // 加载包含 "uglify" 任务的插件。
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-contrib-htmlmin');
  grunt.loadNpmTasks('grunt-usemin');

  // 默认被执行的任务列表。
  //grunt.registerTask('build', ['clean','uglify','cssmin','copy','usemin']);
  grunt.registerTask('build', ['clean','uglify','cssmin']);//,'htmlmin','useminPrepare','usemin','copy',

};