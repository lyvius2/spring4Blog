/**
 * Created by yhwang131 on 2016-08-08.
 */
var gulp = require('gulp'),
	concat = require('gulp-concat'),
	uglify = require('gulp-uglify');

var semanticUiBuild = require('./semantic/tasks/build');
var path = './node_modules';

gulp.task('start', function(){
	return console.log('Start Build Front-End Component!');
});

gulp.task('semanticUiBuild', semanticUiBuild);

gulp.task('vendorJsUglify', function(){
	return gulp.src([path+'/jquery/dist/jquery.js', path+'/angular/angular.js', path+'/underscore/underscore.js'])
		.pipe(concat('vendor.js'))
		.pipe(uglify())
		.pipe(gulp.dest('./scripts'));
});

gulp.task('default',['start','vendorJsUglify','semanticUiBuild']);