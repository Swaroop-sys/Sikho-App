// models/course.model.ts
export interface CourseModel {
  id: number;
  title: string;
  description: string;
  videoUrl: string;
  category: string;
  instructorName: string;
  price: number;
}
